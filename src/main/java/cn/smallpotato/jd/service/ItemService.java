package cn.smallpotato.jd.service;

import cn.smallpotato.jd.pojo.Item;

import java.util.List;

/**
 * @author Panjb
 */
public interface ItemService {

    void save(Item item);

    List<Item> findAll(Item item);
}
