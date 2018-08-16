import React from 'react';

import {HashRouter, Route} from 'react-router-dom'

import AppLayout from './AppLayout'

import DonationFeature from '../donation/DonationFeature'
import MemberFeature from 'eco-feature-member/member/MemberFeature'
import UserFeature from 'eco-feature-user/user/UserFeature'

import AppSettings from './AppSettings'

import {createMuiTheme} from '@material-ui/core/styles';

import purple from '@material-ui/core/colors/purple';

class App extends React.Component {

  get theme() {
    return createMuiTheme({
      palette: {
        primary: purple,
        secondary: {
          main: '#f44336',
        },
      },
    })
  }

  render() {

    return (
      <HashRouter>
        <AppLayout theme={this.theme}>

          <Route path='/' exact render={(props) => (
            <h1>Welcome</h1>
          )}/>

          <Route path='/members' exact render={(props) => (
            <MemberFeature/>
          )}/>

          <Route path='/donations' exact render={(props) => (
            <DonationFeature/>
          )}/>

          <Route path='/users' exact render={(props) => (
            <UserFeature/>
          )}/>

          <Route path='/settings' render={(props) => (
            <AppSettings/>
          )}/>

        </AppLayout>
      </HashRouter>
    )
  }
}

export default App