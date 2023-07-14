package com.asmeydan.service.impl;

import com.asmeydan.dto.UserDto;
import com.asmeydan.entity.Address;
import com.asmeydan.entity.User;
import com.asmeydan.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import com.asmeydan.repository.AddressRepository;
import com.asmeydan.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    @Override
    @Transactional
    public UserDto save(UserDto userDto) {
        //Assert.isNull(userDto.getName(), "isim girilmedi!");

        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        final User userDb = userRepository.save(user);

        List<Address> list = new ArrayList<>();
        userDto.getAddresses().forEach((item)-> {
            Address address = new Address();
            address.setAddress(item);
            address.setAddressType(Address.AddressType.OTHER);
            address.setActive(true);
            address.setUser(userDb);
            list.add(address);
        });
        addressRepository.saveAll(list);
        userDto.setId(userDto.getId());
        return userDto;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();

        users.forEach((item)-> {
            UserDto userDto1 = new UserDto();
            userDto1.setId(item.getId());
            userDto1.setName(item.getName());
            userDto1.setSurname(item.getSurname());
            userDto1.setAddresses(
                    item.getAddresses() != null ?
                    item.getAddresses().stream().map(Address::getAddress).collect(Collectors.toList()) : null);
            userDtos.add(userDto1);
        });
        return userDtos;
    }

    @Override
    public Page<UserDto> getAll(Pageable pageable) {
        return null;
    }
}
