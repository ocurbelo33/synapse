package io.americanexpress.service.book.rest.controller;

import io.americanexpress.service.book.rest.config.BookEndpoints;
import io.americanexpress.service.book.rest.model.CreateBookRequest;
import io.americanexpress.service.book.rest.model.CreateBookResponse;
import io.americanexpress.service.book.rest.service.CreateBookService;
import io.americanexpress.synapse.service.rest.controller.BaseCreateController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BookEndpoints.BOOK_ENDPOINT)
public class CreateBookController extends BaseCreateController<CreateBookRequest, CreateBookResponse, CreateBookService> {
}
