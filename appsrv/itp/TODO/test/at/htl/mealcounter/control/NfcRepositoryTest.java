package at.htl.mealcounter.control;

import io.quarkus.test.junit.QuarkusTest;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class NfcRepositoryTest {

    @Inject
    PersonRepository personRepository;

    @Inject
    NfcRepository nfcRepository;


}
