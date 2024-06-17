package com.example.license;

import com.example.license.repository.TypeRepository;
import com.example.license.model.Type;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TypeIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TypeRepository typeRepository;

    private Type newType;
    private Type existingType;

    @BeforeEach
    public void setUp() {
        newType = new Type();
        newType.setName("Пример типа лицензии");
    }

    @Test
    public void testCreateType() {
        Type savedType = entityManager.persist(newType);
        entityManager.flush();

        Type foundType = typeRepository.findById(savedType.getId()).orElse(null);
        assertThat(foundType).isNotNull();
        assertThat(foundType.getName()).isEqualTo("Пример типа лицензии");
    }

    @Test
    public void testReadType() {
        Type savedType = entityManager.persist(newType);
        entityManager.flush();

        Type foundType = typeRepository.findById(savedType.getId()).orElse(null);
        assertThat(foundType).isNotNull();
    }

    @Test
    public void testUpdateType() {
        Type savedType = entityManager.persist(newType);
        entityManager.flush();

        Type foundType = typeRepository.findById(savedType.getId()).orElse(null);
        foundType.setName("Еще один тип");
        Type updatedType = entityManager.persist(foundType);
        entityManager.flush();

        assertThat(updatedType.getName()).isEqualTo("Еще один тип");
    }

    @Test
    public void testDeleteType() {
        Type savedType = entityManager.persist(newType);
        entityManager.flush();

        typeRepository.delete(savedType);
        Type deletedType = typeRepository.findById(savedType.getId()).orElse(null);
        assertThat(deletedType).isNull();
    }
}
