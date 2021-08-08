package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public List<Note> getAllNotes(int userid) {
        return this.noteMapper.getAllNotes(userid);
    }

    public Integer createNote(Note note) {
        if(note.getNotedescription().length() > 1000) return 0;
        return this.noteMapper.insertNote(note);
    }

    public Integer updateNote(Note note) {
        if(note.getNotedescription().length() > 1000) return 0;
        return this.noteMapper.updateNote(note);
    }

    public void deleteNote(Integer noteid) {
        this.noteMapper.deleteNote(noteid);
    }

}
