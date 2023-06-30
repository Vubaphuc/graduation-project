import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    BarElement,
    Title,
    Tooltip,
    Legend,
    ArcElement,
    LineController,
    LineElement,
    PointElement
} from "chart.js";

ChartJS.register(
    ArcElement,
    CategoryScale,
    LinearScale,
    BarElement,
    LineController,
    LineElement,
    PointElement,
    Title,
    Tooltip,
    Legend,
);

const chartDoughnut = (labels, dataL) => {

    const data = {
        labels: labels,
        datasets: [
            {
                label: '# of Votes',
                data: dataL,
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)',
                    'rgba(0, 128, 0, 0.2)',
                    'rgba(255, 0, 0, 0.2)',
                    'rgba(0, 0, 255, 0.2)',
                    'rgba(128, 128, 128, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)',
                    'rgba(0, 128, 0, 1)',
                    'rgba(255, 0, 0, 1)',
                    'rgba(0, 0, 255, 1)',
                    'rgba(128, 128, 128, 1)'
                ],
                borderWidth: 1,
            },
        ],
    };

    return {
        data
    }


}

export default chartDoughnut;