import { CircularProgress } from "@material-ui/core";
import { withRouter } from "react-router-dom";
import React, { Component } from "react";
import * as ActivityApi from './ActivityApi';
import Card from "@material-ui/core/Card/index";
import Chart from "../charts/Chart";
import css from "../charts/charts.module.scss";
import YearSelect from "./YearSelect";

class Activity extends Component {
    state = {
        chartData: {},
        currentYear: 2019,
        possibleYears: Array.from({ length: 5 }, (a, i) => new Date().getFullYear() - i),
        loading: false,

    };

    componentWillMount() {
        this.createChart();
        this.updateChart();
    }

    changeYear = (year) => {
        this.setState({ currentYear: year });
        this.updateChart(year);
    };

    updateChart = async (year = this.state.currentYear) => {
        this.setState({ loading: true });
        const activity = await ActivityApi.getUserActivity(year);

        const { chartData } = this.state;
        chartData.datasets[0].data = activity.activityPerMonth.map(a => a.numberOfPosts);
        chartData.datasets[1].data = activity.activityPerMonth.map(a => a.numberOfComments);
        chartData.datasets[2].data = activity.activityPerMonth.map(a => a.numberOfAttachments);

        this.setState({ chartData, loading: false })
    };

    createChart() {
        this.setState({
            chartData: {
                labels: ['Styczeń', 'Luty', 'Marzec', 'Kwiecień', 'Maj', 'Czerwiec', 'Lipiec', 'Spierpień', 'Wrzesień', 'Październik', 'Listopad', 'Grudzień'],
                datasets: [{
                    label: 'Posty',
                    data: Array.from({ length: 12 }, () => 0),
                    backgroundColor:
                        Array.from({ length: 12 }, () => 'rgba(255, 99, 132, 0.8)'),

                }, {
                    label: 'Komentarze',
                    data: Array.from({ length: 12 }, () => 0),
                    backgroundColor:
                        Array.from({ length: 12 }, () => 'rgba(54, 162, 235, 0.8)')
                }, {
                    label: 'Pliki',
                    data: Array.from({ length: 12 }, () => 0),
                    backgroundColor:
                        Array.from({ length: 12 }, () => 'rgba(255, 206, 86, 0.8)')
                }],
                options: {
                    scales: {
                        yAxes: [{
                            display: true,
                            ticks: {
                                suggestedMin: 0,    // minimum will be 0, unless there is a lower value.
                                beginAtZero: true,   // minimum value will be 0.
                                stepSize: 1,
                                callback: function (value) { if (Number.isInteger(value)) { return value; } },
                            }
                        }]
                    }
                }
            }
        });
    }

    render() {
        return (
            <>
                <div className={css.yearsSelectWrapper}>
                    <p className={css.yearsSelectText}>Wybierz rok aktywności </p>
                    <YearSelect years={this.state.possibleYears} onSelect={this.changeYear}/>
                </div>
                <Card className={css.chartsWrapper}>
                    {this.state.loading && (<div className={css.loadingWrapper}>
                        <p>Wczytuję dane </p><CircularProgress size={20}/>
                    </div>)}
                    <div className={css.chart}>
                        <Chart chartData={this.state.chartData}/>
                    </div>

                </Card>
            </>
        )
    }

}

export default withRouter(Activity);
