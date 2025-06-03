package xroigmartin.analyzcorp.infrastructure.in.rest.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class PaginatedResponseDTO<T> {
    private long total;
    private int page;
    private int size;
    private List<T> items;
}
