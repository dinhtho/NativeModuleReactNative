import React, { Component } from 'react'
import { Text, View, TouchableOpacity, NativeModules } from 'react-native'
// Styles
import styles from './Styles/LaunchScreenStyles'
const NativeToast = NativeModules.NativeToast
const Camera = NativeModules.Camera

export default class LaunchScreen extends Component {
  render() {
    return (
      <View
        style={styles.mainContainer}>
        <TouchableOpacity
          style={{ backgroundColor: 'red', padding: 10 }}
          onPress={this.toast} >
          <Text>Toast</Text>
        </TouchableOpacity>

        <TouchableOpacity
          style={{ backgroundColor: 'red', padding: 10 }}
          onPress={this.openCamera} >
          <Text>Open Camera</Text>
        </TouchableOpacity>
      </View>
    )
  }

  toast() {
    NativeToast.showMessage('My Native Toast', NativeToast.SHORT);
  }
  openCamera() {
    Camera.takePhoto((response) => {
      console.log('----------------------------------------------------')
      NativeToast.showMessage('Done', NativeToast.SHORT);
    })
  }
}
