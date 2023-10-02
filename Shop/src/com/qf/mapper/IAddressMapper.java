package com.qf.mapper;

import com.qf.pojo.Address;

import java.util.List;

public interface IAddressMapper {

    public void add(Address address);

    public void delete(int id);

    public void update(Address address);

    public void updateById(int id);

    public Address queryById(int id);

    public List<Address> queryByUserId(int userId);

    public List<Address> query();
}
