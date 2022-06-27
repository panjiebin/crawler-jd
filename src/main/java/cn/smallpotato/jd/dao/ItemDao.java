package cn.smallpotato.jd.dao;

import cn.smallpotato.jd.pojo.Item;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Panjb
 */
public interface ItemDao extends JpaRepository<Item, Long> {
}
