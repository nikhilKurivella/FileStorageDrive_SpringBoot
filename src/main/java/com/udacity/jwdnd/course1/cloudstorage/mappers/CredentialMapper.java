package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {
    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userid}")
    List<Credential> getAllCredentials(int userid);

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userid} AND credentialid = #{credentialid}")
    Credential getSingleCredential(int userid, int credentialid);

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES (#{url}, #{username}, #{key}, #{password}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
    int insertCredential(Credential credential);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialid}")
    void deleteCredential(int credentialid);

    @Update("UPDATE CREDENTIALS SET url=#{url}, username = #{username}, key = #{key}, password = #{password} WHERE userid = #{userid} AND credentialid = #{credentialid}")
    int updateCredential(Credential credential);
}
