package xroigmartin.analyzcorp.infrastructure.in.rest.utils;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;
import xroigmartin.analyzcorp.infrastructure.in.rest.dto.PaginatedResponseDTO;

import java.util.function.Function;

@UtilityClass
public class PaginatedResponseUtils {

    public static <T, U>PaginatedResponseDTO<U> toPaginatedResponseDTO(Page<T> page, Function<T, U> mapper){
        var items = page.getContent().stream().map(mapper).toList();
        return PaginatedResponseDTO.<U>builder()
                .items(items)
                .page(page.getNumber())
                .size(page.getSize())
                .total(page.getTotalElements())
                .build();
    }
}
