package com.example.yumii.endpoint;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Forwards Angular client-side routes (e.g. /posts/1) to index.html.
 * Paths starting with /api or containing a dot (static files) are not matched.
 * Covers routes up to two segments deep; add patterns if your routes go deeper.
 */
@Controller
class SpaForwardController {

    @GetMapping({"/{path:^(?!api$)[^.]*}", "/{path:^(?!api$)[^.]*}/{sub:[^.]*}"})
    String forward() {
        return "forward:/index.html";
    }
}
