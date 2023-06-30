import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

function ExportPDF() {
    const { billId } = useParams();

    const handleExportPDF = () => {
        const url = `http://localhost:8080/employee/api/v1/bill-pdf/${billId}`;
        window.location.href = url;
    };



    return (
        <>
            <div className="export-pdf">
                <button className="btn-export-dpf btn-info" onClick={handleExportPDF}>
                    Export INVOICE PDF
                </button>
            </div>
        </>
    );
}

export default ExportPDF;
