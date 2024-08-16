package com.example

import com.example.dao.DataPointsDao

class Repository(val env:Env)  {
  lazy val dataPoints = new DataPointsDao(env)
}
