package com.scarabERP.ERP.han.inventory;

import java.util.List;
import com.scarabERP.ERP.common.QueryVO;
import org.apache.ibatis.annotations.Param;

public interface InventoryDAO {
	public List<InventoryVO> listAll(QueryVO vo);
	public int listAllCount(QueryVO vo);
	
	public List<InventoryVO> listByWarehouse(QueryVO vo, @Param("warehouse_id") int warehouse_id);
	public int listByWarehouseCount(QueryVO vo, @Param("warehouse_id") int warehouse_id);
	
	public List<TradeVO> listRecent(@Param("item") String item, @Param("warehouse") int warehouse);
	
	public List<TradeVO> listAlltrade(QueryVO vo);
	public int listAlltradeCount(QueryVO vo);
	
	public List<InventoryVO> restqnt(String items_id);
}
