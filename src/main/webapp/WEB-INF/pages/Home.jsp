<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TransferHub - Football Transfer Market</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
</head>
<body>
    <%@ include file="header.jsp" %>
    <!-- Hero Section -->
    <section class="hero">
        <img src="${pageContext.request.contextPath}/resources/Homeimages/top.png" alt="Top Transfers" class="hero-img">
        <div class="hero-content">
            <h1 class="hero-title">Summer Transfer Window</h1>
            <p class="hero-subtitle">Discover the hottest talent in the market right now</p>
            <button class="watch-btn">
                <span class="play-icon">▶</span> Watch Highlight Reels
            </button>
        </div>
    </section>

    <!-- News Grid -->
    <div class="news-grid">
        <div class="news-item">
            <img src="${pageContext.request.contextPath}/resources/Homeimages/dean.jpeg" alt="Transfer Record" class="news-img">
            <div class="news-overlay">
                <h3 class="news-title">New transfer record set in Premier League</h3>
                <p class="news-time">2 hours ago</p>
            </div>
        </div>
        <div class="news-item">
            <img src="${pageContext.request.contextPath}/resources/Homeimages/olise.jpeg" alt="Rising Star" class="news-img">
            <div class="news-overlay">
                <h3 class="news-title">Rising star attracts multiple top clubs</h3>
                <p class="news-time">5 hours ago</p>
            </div>
        </div>
        <div class="news-item">
            <img src="${pageContext.request.contextPath}/resources/Homeimages/mbappe.png" alt="Top Striker" class="news-img">
            <div class="news-overlay">
                <h3 class="news-title">Top striker looking for new challenge</h3>
                <p class="news-time">20 hours ago</p>
            </div>
        </div>
        <div class="news-item">
            <img src="${pageContext.request.contextPath}/resources/Homeimages/fabrizio.png" alt="Transfer App" class="news-img">
            <div class="news-overlay">
                <h3 class="news-title">TransferHub launches new mobile app for agents</h3>
                <p class="news-time">1 day ago</p>
            </div>
        </div>
    </div>

    <!-- Players Section -->
    <section class="players-section">
        <h2 class="section-title">Featured Players</h2>
        <div class="players-carousel">
            <div class="player-card" id="player1">
              <div class="player-img-container">
                <img src="${pageContext.request.contextPath}/resources/Homeimages/lamineyamal.png" alt="Lamine Yamal" class="player-img">
                <div class="player-stats-overlay">
                  <div class="stats-row">
                    <div class="stat-item">
                      <div class="stat-value">93</div>
                      <div class="stat-label">Appearances</div>
                    </div>
                    <div class="stat-item">
                      <div class="stat-value">21</div>
                      <div class="stat-label">Goals</div>
                    </div>
                    <div class="stat-item">
                      <div class="stat-value">24</div>
                      <div class="stat-label">Assists</div>
                    </div>
                  </div>
                  <div class="season-info">
                    <span>2024/2025 Season</span>
                    <span>43 Matches</span>
                  </div>
                </div>
              </div>
              <div class="player-info">
                <h3 class="player-name">Lamine Yamal</h3>
                <p class="player-position">Forward</p>
                <p class="player-value">€185M</p>
              </div>
            </div>
            <div class="player-card" id="player2">
              <div class="player-img-container">
                <img src="${pageContext.request.contextPath}/resources/Homeimages/ferrantorres.png" alt="Ferran torres" class="player-img">
                <div class="player-stats-overlay">
                  <div class="stats-row">
                    <div class="stat-item">
                      <div class="stat-value">78</div>
                      <div class="stat-label">Appearances</div>
                    </div>
                    <div class="stat-item">
                      <div class="stat-value">17</div>
                      <div class="stat-label">Goals</div>
                    </div>
                    <div class="stat-item">
                      <div class="stat-value">9</div>
                      <div class="stat-label">Assists</div>
                    </div>
                  </div>
                  <div class="season-info">
                    <span>2024/2025 Season</span>
                    <span>35 Matches</span>
                  </div>
                </div>
              </div>
              <div class="player-info">
                <h3 class="player-name">Ferran torres</h3>
                <p class="player-position">Striker</p>
                <p class="player-value">€70M</p>
              </div>
            </div>
            <div class="player-card" id="player3">
              <div class="player-img-container">
                <img src="${pageContext.request.contextPath}/resources/Homeimages/Robert Lewandoski.png" alt="Robert Lewandowski" class="player-img">
                <div class="player-stats-overlay">
                  <div class="stats-row">
                    <div class="stat-item">
                      <div class="stat-value">102</div>
                      <div class="stat-label">Appearances</div>
                    </div>
                    <div class="stat-item">
                      <div class="stat-value">42</div>
                      <div class="stat-label">Goals</div>
                    </div>
                    <div class="stat-item">
                      <div class="stat-value">12</div>
                      <div class="stat-label">Assists</div>
                    </div>
                  </div>
                  <div class="season-info">
                    <span>2024/2025 Season</span>
                    <span>40 Matches</span>
                  </div>
                </div>
              </div>
              <div class="player-info">
                <h3 class="player-name">Robert Lewandowski</h3>
                <p class="player-position">Forward</p>
                <p class="player-value">€110M</p>
              </div>
            </div>
            <div class="player-card hidden" id="player4">
              <div class="player-img-container">
                <img src="${pageContext.request.contextPath}/resources/Homeimages/wirtz.png" alt="Florian Wirtz" class="player-img">
                <div class="player-stats-overlay">
                  <div class="stats-row">
                    <div class="stat-item">
                      <div class="stat-value">85</div>
                      <div class="stat-label">Appearances</div>
                    </div>
                    <div class="stat-item">
                      <div class="stat-value">18</div>
                      <div class="stat-label">Goals</div>
                    </div>
                    <div class="stat-item">
                      <div class="stat-value">29</div>
                      <div class="stat-label">Assists</div>
                    </div>
                  </div>
                  <div class="season-info">
                    <span>2024/2025 Season</span>
                    <span>38 Matches</span>
                  </div>
                </div>
              </div>
              <div class="player-info">
                <h3 class="player-name">Florian Wirtz</h3>
                <p class="player-position">Attacking Midfielder</p>
                <p class="player-value">€100M</p>
              </div>
            </div>
            <div class="player-card hidden" id="player5">
              <div class="player-img-container">
                <img src="${pageContext.request.contextPath}/resources/Homeimages/musiala.png" alt="Jamal Musiala" class="player-img">
                <div class="player-stats-overlay">
                  <div class="stats-row">
                    <div class="stat-item">
                      <div class="stat-value">92</div>
                      <div class="stat-label">Appearances</div>
                    </div>
                    <div class="stat-item">
                      <div class="stat-value">23</div>
                      <div class="stat-label">Goals</div>
                    </div>
                    <div class="stat-item">
                      <div class="stat-value">20</div>
                      <div class="stat-label">Assists</div>
                    </div>
                  </div>
                  <div class="season-info">
                    <span>2024/2025 Season</span>
                    <span>41 Matches</span>
                  </div>
                </div>
              </div>
              <div class="player-info">
                <h3 class="player-name">Jamal Musiala</h3>
                <p class="player-position">Attacking Midfielder</p>
                <p class="player-value">€130M</p>
              </div>
            </div>
            <div class="player-card hidden" id="player6">
              <div class="player-img-container">
                <img src="${pageContext.request.contextPath}/resources/Homeimages/rodrygo.png" alt="Rodrygo" class="player-img">
                <div class="player-stats-overlay">
                  <div class="stats-row">
                    <div class="stat-item">
                      <div class="stat-value">88</div>
                      <div class="stat-label">Appearances</div>
                    </div>
                    <div class="stat-item">
                      <div class="stat-value">22</div>
                      <div class="stat-label">Goals</div>
                    </div>
                    <div class="stat-item">
                      <div class="stat-value">15</div>
                      <div class="stat-label">Assists</div>
                    </div>
                  </div>
                  <div class="season-info">
                    <span>2024/2025 Season</span>
                    <span>36 Matches</span>
                  </div>
                </div>
              </div>
              <div class="player-info">
                <h3 class="player-name">Rodrygo</h3>
                <p class="player-position">Forward</p>
                <p class="player-value">€100M</p>
              </div>
            </div>
          </div>
          <div class="carousel-nav">
            <button class="nav-btn" id="prev-btn">❮</button>
            <button class="nav-btn" id="next-btn">❯</button>
          </div>
        
        <!-- Additional Action Buttons -->
        <div class="action-buttons">
            <button class="action-btn">Contact Agent</button>
            <button class="action-btn premium-btn">Premium Access</button>
            <button class="action-btn register-btn">Register Player</button>
        </div>
    </section>

    <!-- Stats Section (formerly Trophies) -->
    <section class="trophies-section">
        <div class="trophy-item">
            <span class="trophy-icon">📊</span>
            <div class="trophy-info">
                <div class="trophy-count">5,000+</div>
                <div class="trophy-name">ACTIVE PLAYERS</div>
            </div>
        </div>
        <div class="trophy-item">
            <span class="trophy-icon">🏢</span>
            <div class="trophy-info">
                <div class="trophy-count">500+</div>
                <div class="trophy-name">REGISTERED CLUBS</div>
            </div>
        </div>
        <div class="trophy-item">
            <span class="trophy-icon">👔</span>
            <div class="trophy-info">
                <div class="trophy-count">1,200+</div>
                <div class="trophy-name">LICENSED AGENTS</div>
            </div>
        </div>
        <div class="trophy-item">
            <span class="trophy-icon">💰</span>
            <div class="trophy-info">
                <div class="trophy-count">€2.5B</div>
                <div class="trophy-name">TRANSFER VOLUME</div>
            </div>
        </div>
    </section>

    <!-- Logo Divider -->
    <div class="club-logo-divider">
        <img src="${pageContext.request.contextPath}/resources/Homeimages/logo.png" alt="TransferHub Est. 2023" class="club-year-logo">
    </div>

 
    <!-- Social Media Section -->
    <section class="social-section">
        <h3 class="social-title">Follow TransferHub on social media</h3>
        <div class="social-icons">
            <a href="#" class="social-icon facebook">
                <span>TransferHub</span>
                <span>@transferhub</span>
            </a>
            <a href="#" class="social-icon twitter">
                <span>TransferHub</span>
                <span>@transferhub</span>
            </a>
            <a href="#" class="social-icon youtube">
                <span>TransferHub</span>
                <span>Youtube</span>
            </a>
            <a href="#" class="social-icon instagram">
                <span>TransferHub</span>
                <span>@transferhub</span>
            </a>
            <a href="#" class="social-icon tiktok">
                <span>TransferHub</span>
                <span>TikTok</span>
            </a>
            <a href="#" class="social-icon spotify">
                <span>TransferHub</span>
                <span>Podcast</span>
            </a>
            <a href="#" class="social-icon discord">
                <span>TransferHub</span>
                <span>Community</span>
            </a>
        </div>
    </section>
    <%@ include file="footer.jsp" %>
    <script>
        // Carousel functionality
        document.addEventListener("DOMContentLoaded", function() {
            const carousel = document.querySelector(".players-carousel");
            const cards = document.querySelectorAll(".player-card");
            const prevBtn = document.getElementById("prev-btn");
            const nextBtn = document.getElementById("next-btn");
            let currentIndex = 0;
            const totalPlayers = cards.length;
            const visibleCards = 3;

            function updateCarousel() {
                cards.forEach((card, index) => {
                    if (index >= currentIndex && index < currentIndex + visibleCards) {
                        card.classList.remove("hidden");
                    } else {
                        card.classList.add("hidden");
                    }
                });
            }

            nextBtn.addEventListener("click", function() {
                if (currentIndex + visibleCards < totalPlayers) {
                    currentIndex++;
                    updateCarousel();
                }
            });

            prevBtn.addEventListener("click", function() {
                if (currentIndex > 0) {
                    currentIndex--;
                    updateCarousel();
                }
            });

            // Initialize
            updateCarousel();
        });
    </script>
</body>
</html>