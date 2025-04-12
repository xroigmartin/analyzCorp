CREATE TABLE personal_economy.category_keyword (
    id SERIAL PRIMARY KEY,
    category_id INT NOT NULL,
    keyword VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
    created_by VARCHAR(50) NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
    updated_by VARCHAR(50) NOT NULL,
CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES personal_economy.category (id) ON DELETE CASCADE
);