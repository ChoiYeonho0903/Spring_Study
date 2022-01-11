package org.zerock.ex1.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.ex1.entity.Memo;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemoRepositoryOldTest {

    @Autowired
    MemoRepositoryOld memoRepositoryOld;
    @Autowired
    MemoRepository memoRepository;

    @Test
    @Rollback(value = false)
    public void testInertDummies() throws Exception {
        IntStream.rangeClosed(1,100).forEach(i -> {
            Memo memo = new Memo("Sample_"+i);
            memoRepositoryOld.save(memo);
        });
    }

    @Test
    public void testSelect() throws Exception {
        //given
        Long id = 100L;
        //when
        Memo result = memoRepositoryOld.findById(id);
        //then
        System.out.println("====================");

        System.out.println(result);
    }

    @Test
    @Rollback(value = false)
    public void testUpdate() throws Exception {
        Memo memo = memoRepositoryOld.findById(100L);
        memo.setMemoText("Update Text");
        memoRepositoryOld.save(memo);
    }

    @Test
    @Rollback(value = false)
    public void testDelete() {
        memoRepositoryOld.deleteById(100L);
    }

    @Test
    public void testPageDefault() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Memo> result = memoRepository.findAll(pageable);
        for (Memo memo : result.getContent()) {
            System.out.println(memo);
        }
    }

    @Test
    public void testSort() {
        Sort sort = Sort.by("id").descending();
        Pageable pageable = PageRequest.of(0, 10, sort);
        Page<Memo> result = memoRepository.findAll(pageable);
        result.get().forEach(memo -> {
            System.out.println(memo);
        });
    }

    @Test
    public void testQueryMethods() {
        List<Memo> list = memoRepository.findByIdBetweenOrderByIdDesc(70L, 80L);
        for (Memo memo : list) {
            System.out.println(memo);
        }
    }

    @Test
    public void testQueryMethodWithPagable() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());
        Page<Memo> result = memoRepository.findByIdBetween(10L, 50L, pageable);
        for (Memo memo : result) {
            System.out.println(memo);
        }
    }
}