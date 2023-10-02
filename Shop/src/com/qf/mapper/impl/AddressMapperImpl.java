package com.qf.mapper.impl;

import com.qf.mapper.IAddressMapper;
import com.qf.pojo.Address;
import com.qf.utils.DBUtil;

import java.util.List;

public class AddressMapperImpl implements IAddressMapper {
    @Override
    public void add(Address address) {
        String sql="insert into t_address (shouhuoren,phone,address,userid,isdefault) values(?,?,?,?,?)";
        DBUtil.commonInsert(sql, address.getShouhuoren(), address.getPhone(), address.getAddress(), address.getUserid(),address.getIsdefault());
    }

    @Override
    public void delete(int id) {
        String sql="delete from t_address where id=?";
        DBUtil.commonUpdate(sql, id);
    }

    @Override
    public void update(Address address) {
;        String sql="update t_address set shouhuoren=?,phone=?,address=?,isdefault=? where id=?";
         DBUtil.commonUpdate(sql, address.getShouhuoren(), address.getPhone(), address.getAddress(), address.getIsdefault(), address.getId());
    }

    @Override
    public void updateById(int id) {
        String sql="update t_address set isdefault='是' where id=?";
        DBUtil.commonUpdate(sql,id);
    }

    @Override
    public Address queryById(int id) {
        String sql="select * from t_address where id=?";
        List<Address> addresses = DBUtil.commonQuery(Address.class,sql,id);
        if(addresses.isEmpty()){
            return null;
        }
        return addresses.get(0);
    }

    @Override
    public List<Address> queryByUserId(int userId) {
        String sql="select * from t_address where userid=?";
        List<Address> addresses = DBUtil.commonQuery(Address.class,sql, userId);
        if(addresses.isEmpty()){
            return null;
        }
        return addresses;
    }

    @Override
    public List<Address> query() {
        String sql="select * from t_address";
        List<Address> addresses = DBUtil.commonQuery(Address.class,sql);
        if(addresses.isEmpty()){
            return null;
        }
        return addresses;
    }
}
