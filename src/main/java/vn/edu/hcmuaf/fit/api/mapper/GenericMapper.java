package vn.edu.hcmuaf.fit.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface GenericMapper<S,T> {
    T map(S s);
    default List<T> mapList(List<S> sourceList) {
        return sourceList.stream().map(this::map).collect(Collectors.toList());
    }

}
