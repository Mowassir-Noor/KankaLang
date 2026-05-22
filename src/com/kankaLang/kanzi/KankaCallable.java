package com.kankaLang.kanzi;

import java.util.List;

public interface KankaCallable {
    int arity();
    Object call(Interpreter interpreter, List<Object> arguments);
}
