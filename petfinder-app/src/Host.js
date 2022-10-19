let host = "";

if (process.env.REACT_APP_ENV == "DEV") {
    host = "http://localhost";
} else {
    host = "";
}

export default host;