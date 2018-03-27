package org.rf.test.helper;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;

import java.time.Instant;

public class IdGenerator {

    private final RandomStringGenerator generator;

    public IdGenerator(){
        this.generator = new RandomStringGenerator.Builder()
                .withinRange('0', 'Z')
                .filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS)
                .build();
    }

    public String generateReference() {
        StringBuilder generatedIdBuilder = new StringBuilder();
        generatedIdBuilder.append(Instant.now().toEpochMilli());
        String random = this.generator.generate(3);
        generatedIdBuilder.append(random);
        return generatedIdBuilder.toString();
    }

    public String generateId(String suffix) {
        StringBuilder generatedIdBuilder = new StringBuilder();
        generatedIdBuilder.append(Instant.now().toEpochMilli());
        String random = this.generator.generate(10);
        generatedIdBuilder.append(random);
        generatedIdBuilder.append(StringUtils.upperCase(suffix));
        return generatedIdBuilder.toString();
    }

}
