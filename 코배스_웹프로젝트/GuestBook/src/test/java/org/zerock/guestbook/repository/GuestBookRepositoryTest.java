package org.zerock.guestbook.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.guestbook.entity.GuestBook;
import org.zerock.guestbook.entity.QGuestBook;

import java.awt.print.Pageable;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class GuestBookRepositoryTest {

    @Autowired
    private GuestBookRepository guestBookRepository;

    @Test
    @Rollback(value = false)
    public void insertDummies() throws Exception {

        IntStream.rangeClosed(1, 300).forEach(i -> {
            GuestBook guestBook = GuestBook.builder()
                    .title("Title_" + i)
                    .content("Content_" + i)
                    .writer("user_" + (i % 10))
                    .build();
            System.out.println(guestBookRepository.save(guestBook));
        });
    }

    @Test
    @Rollback(value = false)
    public void updateTest() {
        Optional<GuestBook> result = guestBookRepository.findById(300L);

        if(result.isPresent()) {
            GuestBook guestBook = result.get();

            guestBook.changeTitle("Changed Title");
            guestBook.changeContent("Changed Content");

            guestBookRepository.save(guestBook);
        }
    }

    @Test
    public void testQury1() throws Exception {
        PageRequest pageable = PageRequest.of(0, 10, Sort.by("id").descending());

        QGuestBook qGuestBook = QGuestBook.guestBook;

        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder();

        BooleanExpression expression = qGuestBook.title.contains(keyword);

        builder.and(expression);

        Page<GuestBook> result = guestBookRepository.findAll(builder, pageable);

        result.stream().forEach(guestBook -> {
            System.out.println(guestBook);
        });
    }
}