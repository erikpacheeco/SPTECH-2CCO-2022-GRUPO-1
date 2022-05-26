import facebook from '../Images/facebook.svg'
import instagram from '../Images/instagram.svg'
import twitter from '../Images/twitter.svg'

function Footer() {
    return (
        <footer className="session-footer">
            <div className="container-session-footer">
                <div className="footer-social">
                    <a>
                        <img className="footer-instagram" src={instagram} alt="" />
                    </a>
                    <a>
                        <img className="footer-facebook" src={facebook} alt="" />
                    </a>
                    <a>
                        <img className="footer-twitter" src={twitter} alt="" />
                    </a>
                </div>
                <div className="footer-item">
                    <a>Home</a>
                    <a>Contato</a>
                    <a>Feedback</a>
                    <a>Cadastro</a>
                </div>
                <div className="footer-logo">
                    <span className="footer-logo-nome">
                        <span className="logo-nome-verde">Pet</span>Finder &copy;2022
                    </span>
                </div>
            </div>
        </footer>
    );
}

export default Footer;