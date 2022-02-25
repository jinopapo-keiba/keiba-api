package com.example.demo.utils;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.BiPredicate;

@UtilityClass
public class ListUtils {
    @Nullable
    public <T> T search(T object, List<T> objects,BiPredicate<T, T> equalFunction){
        for(T t : objects){
            if(equalFunction.test(object,t)){
                return t;
            }
        }
        return null;
    }
}
