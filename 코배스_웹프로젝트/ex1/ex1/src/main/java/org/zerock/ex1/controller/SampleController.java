package org.zerock.ex1.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.ex1.dto.SampleDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/sample")
@Log4j2
public class SampleController {

//    @GetMapping("/hello")
//    public String[] hello() {
//        return new String[]{"Hello", "World"};
//    }

    @GetMapping("/ex1")
    public void ex1() {
        log.info("e");
    }

    @GetMapping("/ex2")
    public void exModel(Model model) {
        List<SampleDTO> list = IntStream.rangeClosed(1, 20).asLongStream().mapToObj(i -> {
            SampleDTO dto = new SampleDTO(i, "First_" + i, "Last_" + i, LocalDateTime.now());
            return dto;
        }).collect(Collectors.toList());
        model.addAttribute("list", list);
    }
}
