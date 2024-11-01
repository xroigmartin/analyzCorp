package xroigmartin.analyzcorp_backend.shared.infrastructure.domain.model.pagination;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@AllArgsConstructor
@Builder
public class ApiPageResponse<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 5133385516438866047L;

    private final long totalItems;
    private final long totalPages;
    private final int currentPage;
    private final int currentSize;
    private final boolean isFirstPage;
    private final boolean isLastPage;
    private final T content;


    public ApiPageResponse(){
        this(0L,0L,0,0, true, true, null);
    }

    public ApiPageResponse(long totalItems, long totalPages, int currentPage, int currentSize, T content) {
        this(totalItems, totalPages, currentPage, currentSize,currentPage == 0, currentPage == totalPages - 1, content);
    }

}