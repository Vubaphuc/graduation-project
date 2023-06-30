import React from "react";

function Footer() {

    return (
        <>
            <div className="footer">
                <div className="container-fluid pt-4 px-4">
                    <div className="bg-secondary rounded-top p-4">
                        <div className="row">
                            <div className="col-12 col-sm-6 text-center text-sm-start">
                                © <a href="#" className="text-decoration-none color-while">Your Site Name</a>, All Right Reserved.
                            </div>
                            <div className="col-12 col-sm-6 text-center text-sm-end">
                                Designed By <a href="#" className="text-decoration-none color-while">HTML Codex</a>
                                <br />Distributed By: <a href="#" className="text-decoration-none color-while" target="_blank">ThemeWagon</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}

export default Footer;