package com.moveread.tnmt;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.*;

import com.google.gson.Gson;
import com.moveread.tnmt.Types.*;

public class Client {
  public String url = "https://api2.moveread.com";
  public HttpClient client = HttpClient.newBuilder()
    .followRedirects(HttpClient.Redirect.ALWAYS)
    .build();

  private static String removeTrailing(String s, String suffix) {
    return s.endsWith(suffix) ? s.substring(0, s.length() - 1) : s;
  }
  private static String joinUrl(String... parts) {
    var out = parts[0];
    for (var i = 1; i < parts.length; i++)
      out = out + "/" + removeTrailing(parts[i], "/");
    return out;
  }

  public CompletableFuture<List<Tournament>> getTnmts(@Nullable Integer skip, @Nullable Integer take) {
    var url = this.url + "?";
    if (skip != null)
      url += "skip=" + skip + "&";
    if (take != null)
      url += "take=" + take;

    var uri = URI.create(url);
    var req = HttpRequest.newBuilder(uri).build();

    return this.client.sendAsync(req, HttpResponse.BodyHandlers.ofString())
      .thenApply(HttpResponse::body)
      .thenApply(body -> {
        var gson = new Gson();
        return gson.fromJson(body, Tournament.List);
      });
  }
  public CompletableFuture<List<Tournament>> getTnmts(@Nullable Integer skip) {
    return this.getTnmts(skip, null);
  }
  public CompletableFuture<List<Tournament>> getTnmts() {
    return this.getTnmts(null, null);
  }

  public CompletableFuture<Tournament> getTnmt(String tournId) {
    var uri = URI.create(joinUrl(this.url, tournId));
    var req = HttpRequest.newBuilder(uri).build();

    return this.client.sendAsync(req, HttpResponse.BodyHandlers.ofString())
      .thenApply(HttpResponse::body)
      .thenApply(body -> {
        var gson = new Gson();
        return gson.fromJson(body, Tournament.class);
      });
  }

  public CompletableFuture<Boolean> authorize(String tournId, String token) {
    var uri = URI.create(joinUrl(this.url, "authorize", tournId));
    var req = HttpRequest.newBuilder(uri)
      .header("Authorization", "Bearer " + token)
      .build();
    
    return this.client.sendAsync(req, HttpResponse.BodyHandlers.discarding())
      .thenApply(r -> r.statusCode() == 200);
  }

  public CompletableFuture<Group> getGroup(String tournId, String group) {
    var uri = URI.create(joinUrl(this.url, tournId, group));
    var req = HttpRequest.newBuilder(uri).build();

    return this.client.sendAsync(req, HttpResponse.BodyHandlers.ofString())
      .thenApply(HttpResponse::body)
      .thenApply(body -> {
        var gson = new Gson();
        return gson.fromJson(body, Group.class);
      });
  }

  public CompletableFuture<List<Game>> getRound(String tournId, String group, String round) {
    var uri = URI.create(joinUrl(this.url, tournId, group, round));
    var req = HttpRequest.newBuilder(uri).build();

    return this.client.sendAsync(req, HttpResponse.BodyHandlers.ofString())
      .thenApply(HttpResponse::body)
      .thenApply(body -> {
        var gson = new Gson();
        return gson.fromJson(body, Game.List);
      });
  }

  public CompletableFuture<PGN> getPgn(String tournId, String group, String round, String board) {
    var uri = URI.create(joinUrl(this.url, tournId, group, round, board, "pgn"));
    var req = HttpRequest.newBuilder(uri).build();

    return this.client.sendAsync(req, HttpResponse.BodyHandlers.ofString())
      .thenApply(HttpResponse::body)
      .thenApply(body -> {
        var gson = new Gson();
        return gson.fromJson(body, PGN.class);
      });
  }

  public CompletableFuture<@Nullable List<String>> getImages(String tournId, String group, String round, String board) {
    var uri = URI.create(joinUrl(this.url, tournId, group, round, board, "images"));
    var req = HttpRequest.newBuilder(uri).build();

    return this.client.sendAsync(req, HttpResponse.BodyHandlers.ofString())
      .thenApply(HttpResponse::body)
      .thenApply(body -> {
        var gson = new Gson();
        return gson.fromJson(body, Types.StringList);
      });
  }
}