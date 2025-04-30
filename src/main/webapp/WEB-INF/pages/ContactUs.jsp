<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contact Us - TransferHub</title>
     <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ContactUs.css" >      
</head>
<body>
     <%@ include file="header.jsp" %>
    
    <!-- Start of Contact Page Content -->
    <div class="container">
        <div class="contact-section">
            <div class="contact-form">
                <h2>Get In Touch</h2>
                <form action="#" method="post">
                    <div class="form-group">
                        <label for="name">Name</label>
                        <input type="text" id="name" name="name" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" id="email" name="email" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="phone">Phone Number</label>
                        <input type="tel" id="phone" name="phone" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="message">Message</label>
                        <textarea id="message" name="message" class="form-control" required></textarea>
                    </div>
                    <button type="submit" class="submit-btn">Submit</button>
                </form>
            </div>
            
            <div class="contact-info">
                <h1 class="section-title">Contact <span class="highlight">Us</span></h1>
                <p class="contact-description">
                    For questions, technical assistance, or collaboration opportunities via the contact
                    information provided.
                </p>
                
                <ul class="contact-details">
                    <li>
                        <div class="contact-icon">
                            <span>&#9742;</span>
                        </div>
                        <div class="contact-text">9768448996</div>
                    </li>
                    <li>
                        <div class="contact-icon">
                            <span>&#9993;</span>
                        </div>
                        <div class="contact-text">hello@transferhub.com</div>
                    </li>
                    <li>
                        <div class="contact-icon address">
                            <span>&#9906;</span>
                        </div>
                        <div class="contact-text">123 Lalitpur St., Patan, 241987</div>
                    </li>
                </ul>
            </div>
        </div>
        
        <div class="faq-section">
            <div class="faq-title">
                <h2 class="section-title">Frequently Asked <span class="highlight">Questions</span></h2>
            </div>
            <div class="faq-grid">
                <div class="faq-item">
                    <div class="faq-question">
                        What services does TransferHub offer?
                    </div>
                    <div class="faq-answer">
                        TransferHub provides a comprehensive digital platform for football transfers, including player database management, contract negotiations, analytics, and secure document processing for clubs, agents, and players.
                    </div>
                </div>
                <div class="faq-item">
                    <div class="faq-question">
                        How do I create an account on TransferHub?
                    </div>
                    <div class="faq-answer">
                        You can register on our website by clicking the "Sign Up" button in the top-right corner. Fill out your details and select your role in the football ecosystem. Your account will be verified within 24 hours.
                    </div>
                </div>
                <div class="faq-item">
                    <div class="faq-question">
                        Is TransferHub available worldwide?
                    </div>
                    <div class="faq-answer">
                        Yes, TransferHub is available globally. We currently support transfers in all major football leagues across Europe, South America, North America, Asia, and Africa.
                    </div>
                </div>
                <div class="faq-item">
                    <div class="faq-question">
                        How secure is the platform for sensitive transfer information?
                    </div>
                    <div class="faq-answer">
                        TransferHub employs bank-level encryption and secure data practices. All sensitive information is protected with advanced security protocols, and we are compliant with international data protection regulations.
                    </div>
                </div>
            </div>
        </div>
        
        <div class="testimonial-section">
            <div class="faq-title">
                <h2 class="section-title">What Our <span class="highlight">Clients Say</span></h2>
            </div>
            <div class="faq-grid">
                <div class="faq-item">
                    <div class="faq-question">
                        FC Barcelona
                    </div>
                    <div class="faq-answer">
                        "TransferHub has revolutionized our transfer operations. The platform's analytics and streamlined process have saved us countless hours and improved our decision-making."
                    </div>
                </div>
                <div class="faq-item">
                    <div class="faq-question">
                        Premier Sports Agency
                    </div>
                    <div class="faq-answer">
                        "As agents, we've found TransferHub to be an essential tool for managing our clients' career moves. The transparent communication tools make negotiations smoother than ever."
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- End of Contact Page Content -->
    
    <%@ include file="footer.jsp" %>
</body>
</html>