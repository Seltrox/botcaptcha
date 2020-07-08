package de.seltrox.botcaptcha.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/*
 * This class will generate a String which can be used as a CodeBuilder
 * You can customize the code properties.
 */
public class CodeBuilder {

    @SuppressWarnings("unused")
    private final static Random random = new Random();

    private final static List<Character> LOWER_CAPS, UPPER_CAPS, DIGITS, SPECIALS;

    private List<Template> templateList = new ArrayList<Template>();

    private boolean doShuffle;

    public static CodeBuilder builder() {
        return new CodeBuilder();
    }

    public CodeBuilder lowercase(int count) {
        templateList.add(new Template(LOWER_CAPS, count));
        return this;
    }

    public CodeBuilder uppercase(int count) {
        templateList.add(new Template(UPPER_CAPS, count));
        return this;
    }

    public CodeBuilder digits(int count) {
        templateList.add(new Template(DIGITS, count));
        return this;
    }

    public CodeBuilder specials(int count) {
        templateList.add(new Template(SPECIALS, count));
        return this;
    }

    public CodeBuilder shuffle() {
        doShuffle = true;
        return this;
    }

    public String build() {
        StringBuilder CodeBuilder = new StringBuilder();
        List<Character> characters = new ArrayList<>();

        for (Template template : templateList) {
            characters.addAll(template.take());
        }

        if (doShuffle)
            Collections.shuffle(characters);

        for (char chr : characters) {
            CodeBuilder.append(chr);
        }

        return CodeBuilder.toString();
    }

    // initialize statics
    static {
        LOWER_CAPS = new ArrayList<>(26);
        UPPER_CAPS = new ArrayList<>(26);
        for (int i = 0; i < 26; i++) {
            LOWER_CAPS.add((char) (i + 'a'));
            UPPER_CAPS.add((char) (i + 'A'));
        }

        DIGITS = new ArrayList<Character>(10);
        for (int i = 0; i < 10; i++) {
            DIGITS.add((char) (i + '0'));
        }
        
        SPECIALS = new ArrayList<Character>() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            {
                add('!');
                add('@');
                add('#');
                add('$');
                add('%');
                add('^');
                add('&');
                add('(');
                add(')');
                add('*');
                add('+');
            }
        };
    }

    public class Template {
        private final List<Character> source;
        private final int count;

        private final Random random = new Random();

        public Template(List<Character> source, int count) {
            this.source = source;
            this.count = count;
        }

        public List<Character> take() {
            List<Character> taken = new ArrayList<Character>(count);
            for (int i = 0; i < count; i++) {
                taken.add(source.get(random.nextInt(source.size())));
            }
            return taken;
        }
    }

}