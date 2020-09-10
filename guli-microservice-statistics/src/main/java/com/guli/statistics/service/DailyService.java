package com.guli.statistics.service;

import com.guli.statistics.entity.Daily;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author Helen
 * @since 2020-09-10
 */
public interface DailyService extends IService<Daily> {

    void createStatisticsByDay(String day);
}
