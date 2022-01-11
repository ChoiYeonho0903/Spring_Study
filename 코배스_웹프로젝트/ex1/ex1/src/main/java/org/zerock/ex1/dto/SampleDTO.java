package org.zerock.ex1.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SampleDTO {
    private Long sno;
    private String first;
    private String last;
    private LocalDateTime regTime;

    public SampleDTO(Long sno, String first, String last, LocalDateTime regTime) {
        this.sno = sno;
        this.first = first;
        this.last = last;
        this.regTime = regTime;
    }
}
