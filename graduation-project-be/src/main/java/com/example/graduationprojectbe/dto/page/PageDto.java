package com.example.graduationprojectbe.dto.page;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageDto<T> {
    private int currentPage;
    private int pageSize;
    private int totalPages;
    private int totalItems;
    private List<T> data;
}
