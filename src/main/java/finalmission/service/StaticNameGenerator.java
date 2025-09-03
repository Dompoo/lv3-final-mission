package finalmission.service;

import org.springframework.context.annotation.Fallback;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Fallback
@Service
public class StaticNameGenerator implements NameGenerator {

    private static final Random random = new Random();
    private static final List<String> prefix = List.of("졸린", "배고픈", "빠른");
    private static final List<String> suffix = List.of("멍멍이", "고양이", "하마");

    @Override
    public String generate() {
        String selectedPrefix = prefix.get(random.nextInt(prefix.size()));
        String selectedSuffix = suffix.get(random.nextInt(suffix.size()));
        return selectedPrefix + " " + selectedSuffix;
    }
}
