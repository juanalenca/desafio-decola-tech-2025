package me.dio.service.impl;

import me.dio.domain.model.Book;
import me.dio.domain.repository.BookRepository;
import me.dio.service.BookService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Book create(Book bookToCreate) {
        if (bookRepository.existsByIsbn(bookToCreate.getIsbn())) {
            throw new IllegalArgumentException("This ISBN already exists.");
        }
        return bookRepository.save(bookToCreate);
    }
}