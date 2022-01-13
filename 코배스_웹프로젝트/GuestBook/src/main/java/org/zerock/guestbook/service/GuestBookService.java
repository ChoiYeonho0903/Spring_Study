package org.zerock.guestbook.service;

import org.zerock.guestbook.dto.GuestBookDTO;
import org.zerock.guestbook.dto.PageRequestDTO;
import org.zerock.guestbook.dto.PageResultDTO;
import org.zerock.guestbook.entity.GuestBook;

public interface GuestBookService {
    Long register(GuestBookDTO dto);

    PageResultDTO<GuestBookDTO, GuestBook> getList(PageRequestDTO requestDTO);

    default GuestBook dtoToEntity(GuestBookDTO dto) {
        GuestBook entity = GuestBook.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();
        return entity;
    }

    default GuestBookDTO entityToDto(GuestBook entity) {
        GuestBookDTO dto = GuestBookDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();

        return dto;
    }
}
