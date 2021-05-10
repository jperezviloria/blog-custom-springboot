package dev.protobot.blogcustom.service.implementation;

import org.springframework.stereotype.Service;
import org. thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
public class MailContentBuilderServiceImplementation {

    private final TemplateEngine templateEngine;

    public MailContentBuilderServiceImplementation(TemplateEngine templateEngine){
        this.templateEngine = templateEngine;
    }

    public String build(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        return templateEngine.process("mailTemplate", context);
    }
}
