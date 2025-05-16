<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>About Us - TransferHub</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/AboutUs.css">
</head>
<body>
   <%@ include file="header.jsp" %>
    
    <!-- Start of About Page Content -->
    <div class="container">
        <div class="bg-circle bg-circle-1"></div>
        <div class="bg-circle bg-circle-2"></div>
        <div class="diagonal-stripe diagonal-stripe-1"></div>
        <div class="diagonal-stripe diagonal-stripe-2"></div>
        <div class="about-section">
            <div class="about-image">
                <img src="${pageContext.request.contextPath}/resources/Homeimages/logo.png" alt="TransferHub Team">
            </div>
            <div class="about-content">
                <div class="about-badge">About TransferHub</div>
                <h1 class="section-title">The Story Of <span class="highlight">Our Business</span> Journey</h1>
                <p class="section-description">
                    "Our journey aims to transform football transfers with innovative solutions, connecting clubs, agents, and players seamlessly. At TransferHub, we're passionate about revolutionizing the football transfer market through cutting-edge technology and transparent processes."
                </p>
                <p class="section-description">
                    Founded in 2020, TransferHub has quickly become the go-to platform for football professionals looking to streamline the complex world of player transfers. Our dedication to innovation and excellence has positioned us as industry leaders in football transfer technology.
                </p>
            </div>
        </div>
        
        <div class="vision-mission">
            <div class="card">
                <h3>Vision</h3>
                <ul class="card-list">
                    <li>Revolutionize player transfers worldwide through technology</li>
                    <li>Empower clubs with digital solutions for smarter decision-making</li>
                    <li>Create a connected transfer ecosystem that benefits all stakeholders</li>
                    <li>Set new standards for transparency in football transfers</li>
                </ul>
            </div>
            <div class="card">
                <h3>Mission</h3>
                <ul class="card-list">
                    <li>Simplify football transfer processes through our intuitive platform</li>
                    <li>Deliver real-time market insights to clubs and agents</li>
                    <li>Build trust amongst the football stakeholders with secure technology</li>
                    <li>Reduce administrative burdens in transfer negotiations</li>
                </ul>
            </div>
        </div>
        
        <div class="team-section">
            <div class="team-title">
                <h2 class="section-title">Meet Our <span class="highlight">Team</span></h2>
                <p class="section-description">Passionate experts committed to transforming the football transfer market</p>
            </div>
            <div class="team-grid">
                <div class="team-member">
                    <img src="${pageContext.request.contextPath}/resources/extras/samir.jpeg" class="member-img">
                    <div class="member-info">
                        <h3 class="member-name">Samir Adhikari</h3>
                        <p class="member-role">Chief Executive Officer</p>
                        <p class="member-bio">With over 15 years in sports technology, Samir brings vision and leadership to TransferHub's operations.</p>
                    </div>
                </div>
                <div class="team-member">
                    <img src="${pageContext.request.contextPath}/resources/extras/suman.jpeg" alt="Team Member" class="member-img">
                    <div class="member-info">
                        <h3 class="member-name">Suman Lama</h3>
                        <p class="member-role">Chief Technology Officer</p>
                        <p class="member-bio">Suman's expertise in AI and blockchain technology drives TransferHub's innovative solutions.</p>
                    </div>
                </div>
                <div class="team-member">
                    <img src="${pageContext.request.contextPath}/resources/extras/suyog.jpeg" alt="Team Member" class="member-img">
                    <div class="member-info">
                        <h3 class="member-name">Suyog Shrestha</h3>
                        <p class="member-role">Head of Football Relations</p>
                        <p class="member-bio">Former football scout suyog connects TransferHub with clubs and agencies worldwide.</p>
                    </div>
                </div>
                <div class="team-member">
                    <img src="${pageContext.request.contextPath}/resources/extras/prasiddha.jpeg" alt="Team Member" class="member-img">
                    <div class="member-info">
                        <h3 class="member-name">Prasiddha Rawal</h3>
                        <p class="member-role">Lead Product Designer</p>
                        <p class="member-bio">Prasiddha ensures TransferHub delivers an intuitive user experience for all platform users.</p>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="journey-section">
            <div class="journey-title">
                <h2 class="section-title">Our <span class="highlight">Journey</span></h2>
                <p class="section-description">Milestones that shaped TransferHub's growth</p>
            </div>
            <div class="timeline">
                <div class="timeline-item">
                    <div class="timeline-content">
                        <p class="timeline-date">2020</p>
                        <h3>Foundation</h3>
                        <p>TransferHub was launched with a mission to revolutionize football transfers</p>
                    </div>
                </div>
                <div class="timeline-item">
                    <div class="timeline-content">
                        <p class="timeline-date">2021</p>
                        <h3>First Major Partnerships</h3>
                        <p>Partnered with 15 professional clubs across Europe</p>
                    </div>
                </div>
                <div class="timeline-item">
                    <div class="timeline-content">
                        <p class="timeline-date">2022</p>
                        <h3>Technology Breakthrough</h3>
                        <p>Launched our proprietary transfer analytics engine</p>
                    </div>
                </div>
                <div class="timeline-item">
                    <div class="timeline-content">
                        <p class="timeline-date">2023</p>
                        <h3>Global Expansion</h3>
                        <p>Extended our services to South America and Asian markets</p>
                    </div>
                </div>
                <div class="timeline-item">
                    <div class="timeline-content">
                        <p class="timeline-date">2024</p>
                        <h3>Industry Recognition</h3>
                        <p>Named "Sports Tech Company of the Year" at the Football Innovation Awards</p>
                    </div>
                </div>
                <div class="timeline-item">
                    <div class="timeline-content">
                        <p class="timeline-date">2025</p>
                        <h3>The Future</h3>
                        <p>Continuing to innovate and reshape football transfers globally</p>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="shape-divider">
            <svg data-name="Layer 1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1200 120" preserveAspectRatio="none">
                <path d="M321.39,56.44c58-10.79,114.16-30.13,172-41.86,82.39-16.72,168.19-17.73,250.45-.39C823.78,31,906.67,72,985.66,92.83c70.05,18.48,146.53,26.09,214.34,3V0H0V27.35A600.21,600.21,0,0,0,321.39,56.44Z" class="shape-fill"></path>
            </svg>
        </div>
    </div>
    <!-- End of About Page Content -->
    
    <%@ include file="footer.jsp" %>
</body>
</html>