package com.example.mytest.controller;

import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * 群组拖动排序
 *
 * @author 邵益炯
 * @date 2018/8/3
 */
@Data
public class DragSortReq  {

    private List<Group> groupList;

    private String firstName;

    @Validated
    @Data
    public static class Group {
        /**
         * 群组id
         */
        @NotNull(message = "群组id不能为空")
        Integer groupId;
        /**
         * 顺序 最小为0
         */
        @Min(value = 0, message = "顺序必须从0开始")
        Integer index;
    }

}