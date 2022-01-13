package org.zerock.guestbook.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.guestbook.dto.GuestBookDTO;
import org.zerock.guestbook.dto.PageRequestDTO;
import org.zerock.guestbook.dto.PageResultDTO;
import org.zerock.guestbook.entity.GuestBook;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class GuestBookServiceImplTest {

    @Autowired
    private GuestBookService service;

    @Test
    public void testRegister() throws Exception {
        GuestBookDTO guestBookDTO = GuestBookDTO.builder()
                .title("Sample")
                .content("Sample")
                .writer("user0")
                .build();

        System.out.println(service.register(guestBookDTO));
    }

    @Test
    public void testList() throws Exception {
        PageRequestDTO pageRequestDTO = PageRequestDTO
                .builder()
                .page(1)
                .size(10)
                .build();

        PageResultDTO<GuestBookDTO, GuestBook> resultDTO = service.getList(pageRequestDTO);
        System.out.println(resultDTO.isPrev());
        System.out.println(resultDTO.isNext());
        System.out.println(resultDTO.getTotalPage());
        System.out.println("----------------------------");
        for (GuestBookDTO guestBookDTO : resultDTO.getDtoList()) {
            System.out.println(guestBookDTO);
        }
        System.out.println("=============================");
        resultDTO.getPageList().forEach(i -> System.out.println(i));
    }
}