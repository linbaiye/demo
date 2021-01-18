package com.example.demo.controller;

import com.example.demo.controller.mapper.LoanResponseMapper;
import com.example.demo.controller.request.LoanDTO;
import com.example.demo.controller.response.QueryLoanRespDTO;
import com.example.demo.model.LoanRepository;
import com.example.demo.service.LoanService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author tao.lin
 * @date 2020/12/29
 */
@RestController
@AllArgsConstructor
@Slf4j
public class LoanController {

    private final LoanService loanService;

    private final LoanResponseMapper loanResponseMapper;

    private final LoanRepository loanRepository;

    @PostMapping(value = "/accept")
    public String accept(@Validated @RequestBody LoanDTO loanDTO) {
        try {
            loanService.accept(loanDTO);
            return "OK";
        } catch (Exception e) {
            log.error("exception", e);
            return "BAD";
        }
    }


    @PostMapping(value = "/query")
    public @ResponseBody QueryLoanRespDTO query(@RequestParam(name = "applicationNo") String applicationNo) {
        try {
            return loanResponseMapper.map(loanRepository.findByNo(applicationNo)
                    .orElseThrow(IllegalArgumentException::new));
        } catch (Exception e) {
            log.error("error: ", e);
            return null;
        }
    }

}

