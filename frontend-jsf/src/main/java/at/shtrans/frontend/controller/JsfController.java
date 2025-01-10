package at.shtrans.frontend.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Scope(value = "session")
@Component(value = "jsfController")
public class JsfController {

    Logger logger = LoggerFactory.getLogger(JsfController.class);

    public String loadTodoPage() {
        logger.warn("A WARN Message");
        checkPermission();
        return "/todo.xhtml";
    }

    private void checkPermission() {
        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");
    }

}