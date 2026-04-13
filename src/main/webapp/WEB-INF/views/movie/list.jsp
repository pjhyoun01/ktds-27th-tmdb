<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"  %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/css/tmdb.css">
    <title>Movie List</title>
  </head>
  <body>
    <div class="total-count">${totalCount}개 있음</div>
    <a href="/insert" class="insert-btn">영화 등록</a>
    <a href="/regist" class="insert-btn">회원 가입</a>
    <c:if test="${empty searchResult}">
      <div>등록된 영화가 없습니다.</div>
    </c:if>
    <c:forEach var="movie" items="${searchResult}">
      <a href="/view/${movie.movieId}" class="view-link">
        <div class="view">
          <div class="poster-url">
            <c:if test="${not empty movie.obfuscateName}">
              <img src="/files/${movie.obfuscateName}">
            </c:if>
          </div>
          <div class="movie-info">
            <div class="title">${movie.title}</div>
            <div class="movie-meta">
              <span class="open-date">${movie.openDate}</span>
              <span class="running-time">${movie.runningTime} 분</span>
              <span class="state">${movie.state}</span>
            </div>
          </div>
        </div>
      </a>
    </c:forEach>
  </body>
</html>
