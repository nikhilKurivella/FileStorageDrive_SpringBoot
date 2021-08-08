package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM NOTES WHERE userid = #{userid}")
    List<Note> getAllNotes(int userid);

    @Select("SELECT * FROM NOTES WHERE userid = #{userid} AND noteid = #{noteid}")
    Note getSingleNote(int userid, int noteid);

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES (#{notetitle}, #{notedescription}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    int insertNote(Note note);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteid}")
    void deleteNote(int noteid);

    @Update("UPDATE NOTES SET notetitle=#{notetitle}, notedescription = #{notedescription} WHERE userid = #{userid} AND noteid = #{noteid}")
    int updateNote(Note note);

}
