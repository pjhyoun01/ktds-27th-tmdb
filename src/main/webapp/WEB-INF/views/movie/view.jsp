<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/css/tmdb.css">
    <title>Movie View</title>
  </head>
  <body>
    <a href="/" class="back-link">목록으로</a>
    <div class="movie-detail">
      <div class="movie-detail-top">
        <div class="poster-url">
          <c:if test="${not empty movie.obfuscateName}">
            <img src="/files/${movie.obfuscateName}">
          </c:if>
        </div>
        <div class="movie-detail-info">
          <div class="content">${movie.title}</div>
          <div class="content">${movie.openDate} (${movie.openCountry}) / {장르 위치} /${movie.runningTime} 분 </div>
          <div class="content-block introduce">${movie.introduce}</div>
          <span class="synopsis-title">개요</span>
          <div class="content-block sybnopsis-content">${movie.synopsis}</div>
          <div class="content-block make">
            <ul class="staff-list">
              <li class="staff-item">
                <p class="name">{Director 이름}</p>
                <p class="role">Director</p>
              </li>
              <li class="staff-item">
                <p class="name">{Writer 이름}</p>
                <p class="role">Writer</p>
              </li>
              <li class="staff-item">
                <p class="name">{Writer 이름}</p>
                <p class="role">Writer</p>
              </li>
            </ul>
          </div>
        </div>
      </div>

      <div class="movie-detail-bottom">
        <div class="movie-detail-actor">
          <div class="section-title">주요 출연진</div>
          <div class="actor">
            <div class="actor-img">{출연진 사진}</div>
            <div class="actor-name">{출연진 이름}</div>
            <div class="appearance-name">{출연진 배역}</div>
          </div>
        </div>
        <div class="movie-detail-make">
          <div class="movie-detail-meta">
            <div class="movie-detail-meta-item">
              <div class="meta-label">원제</div>
              <div class="meta-value">${movie.originalTitle}</div>
            </div>
            <div class="movie-detail-meta-item">
              <div class="meta-label">상태</div>
              <div class="meta-value">${movie.state}</div>
            </div>
            <div class="movie-detail-meta-item">
              <div class="meta-label">원어</div>
              <div class="meta-value">${movie.language}</div>
            </div>
            <div class="movie-detail-meta-item">
              <div class="meta-label">제작비</div>
              <div class="meta-value">$${movie.budget}</div>
            </div>
            <div class="movie-detail-meta-item">
              <div class="meta-label">수익</div>
              <div class="meta-value">$${movie.profit}</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
