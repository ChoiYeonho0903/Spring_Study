package org.zerock.guestbook.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.guestbook.dto.GuestBookDTO;
import org.zerock.guestbook.dto.PageRequestDTO;
import org.zerock.guestbook.dto.PageResultDTO;
import org.zerock.guestbook.entity.GuestBook;
import org.zerock.guestbook.repository.GuestBookRepository;

import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class GuestBookServiceImpl implements GuestBookService {

    private final GuestBookRepository repository;

    @Override
    public Long register(GuestBookDTO dto) {

        log.info(dto);
        GuestBook entity = dtoToEntity(dto);
        log.info(entity);

        repository.save(entity);

        return entity.getId();
    }

    @Override
    public PageResultDTO<GuestBookDTO, GuestBook> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("id").descending());
        Page<GuestBook> result = repository.findAll(pageable);
        Function<GuestBook, GuestBookDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    }
}
