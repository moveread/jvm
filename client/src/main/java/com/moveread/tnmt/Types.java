package com.moveread.tnmt;

import java.lang.reflect.Type;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.google.gson.reflect.TypeToken;

public class Types {
  public static class Tournament {
    public String tournId;
    public String name;
    public String site;
    public String start_date;
    public String end_date;
    public List<String> groups;
    
    public static Type List = new TypeToken<List<Tournament>>(){}.getType();
  }

  public static class Pairing {
    public String white;
    public String black;
    public @Nullable String result;
  }

  public static class Group {
    public String tournId;
    public String name;
    public List<String> rounds;
  }

  public static class Game {
    public String board;
    public Pairing pairing;
    public @Nullable String status;

    public static Type List = new TypeToken<List<Game>>(){}.getType();
  }

  public static class PGN {
    public List<String> moves;
    public @Nullable boolean early;
  }

  public static Type StringList = new TypeToken<List<String>>(){}.getType();
}
