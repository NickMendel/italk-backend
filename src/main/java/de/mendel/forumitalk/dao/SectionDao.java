package de.mendel.forumitalk.dao;

import de.mendel.forumitalk.model.Section;
import de.mendel.forumitalk.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SectionDao {

    private final SectionRepository repository;

    @Autowired
    public SectionDao(SectionRepository repository) {
        this.repository = repository;
    }

    public Section save(Section section) {
        return repository.save(section);
    }

    public Section findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Section findByTitle(String title) {
        return repository.findByTitle(title);
    }

    public void delete(Section section) {
        repository.delete(section);
    }

    public List<Section> findAll() {
        return repository.findAll();
    }
}
