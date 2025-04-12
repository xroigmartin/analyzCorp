drop table personal_economy.transaction_category;

alter table personal_economy.transaction
add column category_id INTEGER;

update personal_economy.transaction
set category_id = (select id from personal_economy.category where name = 'Gastos Varios')
where transaction.category_id is null;

alter table personal_economy.transaction
alter column category_id set not null;

alter table personal_economy.transaction
add constraint fk_transaction_category foreign key (category_id)
references personal_economy.category(id) on delete set default;

create or replace function set_default_category_for_transaction()
    returns trigger as $$
declare
    default_category_id INT;
begin
    select id into default_category_id
    from personal_economy.category
    where name = 'Gastos Varios';

    if new.category_id is null then
        new.category_id := default_category_id;
    end if;

    return new;
end;
$$ language plpgsql;

create trigger before_insert_transaction
    before insert on personal_economy.transaction
    for each row
    execute function set_default_category_for_transaction();
