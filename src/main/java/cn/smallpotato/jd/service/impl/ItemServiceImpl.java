package cn.smallpotato.jd.service.impl;

import cn.smallpotato.jd.dao.ItemDao;
import cn.smallpotato.jd.pojo.Item;
import cn.smallpotato.jd.service.ItemService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Panjb
 */
@Service
public class ItemServiceImpl implements ItemService {

    private final ItemDao itemDao;

    public ItemServiceImpl(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    @Override
    public void save(Item item) {
        this.itemDao.save(item);
    }

    @Override
    public List<Item> findAll(Item item) {
        Example<Item> example = Example.of(item);
        return this.itemDao.findAll(example);
    }
}
