"use client";

import { Badge } from "@/components/ui/badge";
import { Button } from "@/components/ui/button";
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card";
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs";
import { motion } from "framer-motion";
import { Bell, Code2, Download, ExternalLink, Globe, Layout, Rocket, ShieldCheck, Zap } from "lucide-react";

export default function Home() {
  const integrationSteps = [
    {
      title: "Add Dependency",
      description: "Include the LetsNotify SDK in your module-level build.gradle.",
      code: `dependencies {\n  implementation project(':letsnotify-sdk')\n}`
    },
    {
      title: "Initialize SDK",
      description: "Call initialize in your Application class.",
      code: `LetsNotify.initialize(this, "YOUR_API_KEY")`
    },
    {
      title: "Identify Users",
      description: "Connect notifications to your user database.",
      code: `LetsNotify.setUserId("user_12345")`
    }
  ];

  return (
    <div className="min-h-screen bg-slate-950 text-slate-50 font-sans selection:bg-indigo-500/30">
      {/* Navigation */}
      <nav className="border-b border-slate-800 bg-slate-900/50 backdrop-blur-md sticky top-0 z-50">
        <div className="max-w-7xl mx-auto px-6 h-16 flex items-center justify-between">
          <div className="flex items-center gap-2">
            <div className="bg-indigo-600 p-1.5 rounded-lg">
              <Bell className="w-5 h-5 text-white" />
            </div>
            <span className="font-bold text-xl tracking-tight">LetsNotify</span>
            <Badge variant="outline" className="ml-2 border-slate-700 text-slate-400">SDK v1.0.0</Badge>
          </div>
          <div className="hidden md:flex items-center gap-8 text-sm font-medium text-slate-400">
            <a href="#features" className="hover:text-white transition-colors">Features</a>
            <a href="#docs" className="hover:text-white transition-colors">Documentation</a>
            <Button variant="outline" className="border-slate-700 bg-transparent hover:bg-slate-800">
              <Download className="w-4 h-4 mr-2" /> SDK
            </Button>
          </div>
        </div>
      </nav>

      {/* Hero Section */}
      <section className="relative pt-24 pb-20 overflow-hidden">
        <div className="absolute inset-0 bg-[radial-gradient(circle_at_50%_50%,rgba(79,70,229,0.1),transparent)]" />
        <div className="max-w-7xl mx-auto px-6 relative">
          <motion.div 
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            className="text-center space-y-8 max-w-3xl mx-auto"
          >
            <Badge variant="secondary" className="bg-indigo-500/10 text-indigo-400 border-indigo-500/20 px-4 py-1">
              Production-Ready Android SDK
            </Badge>
            <h1 className="text-5xl md:text-7xl font-extrabold tracking-tight bg-gradient-to-b from-white to-slate-400 bg-clip-text text-transparent">
              Push Notifications <br /> simplified for Android.
            </h1>
            <p className="text-xl text-slate-400 leading-relaxed">
              Integrate LetsNotify in less than 5 minutes. Control delivery, tracking, and segmentation with a single, production-grade SDK.
            </p>
            <div className="flex flex-col sm:flex-row items-center justify-center gap-4">
              <Button size="lg" className="bg-indigo-600 hover:bg-indigo-500 text-white px-8 h-14 rounded-xl text-lg font-semibold">
                Get Started Free
              </Button>
              <Button size="lg" variant="outline" className="border-slate-700 h-14 px-8 rounded-xl text-lg hover:bg-slate-900">
                View Documentation
              </Button>
            </div>
          </motion.div>
        </div>
      </section>

      {/* Features Grid */}
      <section id="features" className="py-24 bg-slate-900/50">
        <div className="max-w-7xl mx-auto px-6">
          <div className="grid md:grid-cols-3 gap-8">
            <FeatureCard 
              icon={<Zap className="w-6 h-6 text-yellow-400" />}
              title="Ultra-Fast Integration"
              description="One-line initialization. No complex Firebase boilerplate required in your host application."
            />
            <FeatureCard 
              icon={<ShieldCheck className="w-6 h-6 text-green-400" />}
              title="Production-Grade Stable"
              description="Built with defensive programming. Non-blocking API calls and silent failure handling for zero crashes."
            />
            <FeatureCard 
              icon={<Globe className="w-6 h-6 text-blue-400" />}
              title="Real-time Tracking"
              description="Automatic 'delivered' and 'opened' event tracking to monitor your campaign performance."
            />
          </div>
        </div>
      </section>

      {/* Integration Section */}
      <section id="docs" className="py-24">
        <div className="max-w-7xl mx-auto px-6">
          <div className="grid lg:grid-cols-2 gap-16 items-start">
            <div className="space-y-8">
              <h2 className="text-4xl font-bold">5 Minute Integration</h2>
              <p className="text-slate-400 text-lg">
                We've designed the LetsNotify SDK to be as intuitive as possible. Follow these simple steps to start sending notifications.
              </p>
              
              <div className="space-y-6">
                {integrationSteps.map((step, idx) => (
                  <div key={idx} className="flex gap-4">
                    <div className="flex-shrink-0 w-8 h-8 rounded-full bg-indigo-600/20 text-indigo-400 border border-indigo-500/30 flex items-center justify-center font-bold">
                      {idx + 1}
                    </div>
                    <div className="space-y-2">
                      <h3 className="font-semibold text-xl">{step.title}</h3>
                      <p className="text-slate-400">{step.description}</p>
                    </div>
                  </div>
                ))}
              </div>
            </div>

            <Card className="bg-slate-900 border-slate-800 overflow-hidden shadow-2xl shadow-indigo-500/10">
              <Tabs defaultValue="kotlin" className="w-full">
                <div className="px-4 py-2 bg-slate-800/50 border-b border-slate-800 flex items-center justify-between">
                  <TabsList className="bg-transparent border-none">
                    <TabsTrigger value="kotlin" className="data-[state=active]:bg-slate-700 data-[state=active]:text-white">Kotlin</TabsTrigger>
                  </TabsList>
                  <Code2 className="w-4 h-4 text-slate-500" />
                </div>
                <TabsContent value="kotlin" className="p-0 m-0">
                  <pre className="p-6 text-sm font-mono leading-relaxed overflow-x-auto text-indigo-300">
                    <code>{`// 1. Initialize
LetsNotify.initialize(this, "ln_prod_82x1...")

// 2. Set User Context
LetsNotify.setUserId("dev_user_99")

// 3. Add Custom Segments
val tags = mapOf(
    "plan" to "enterprise",
    "region" to "us-east"
)
LetsNotify.setTags(tags)

// 4. Opt-out/in
LetsNotify.disablePush()`}</code>
                  </pre>
                </TabsContent>
              </Tabs>
            </Card>
          </div>
        </div>
      </section>

      {/* Footer */}
      <footer className="border-t border-slate-800 py-12 bg-slate-950">
        <div className="max-w-7xl mx-auto px-6 flex flex-col md:flex-row justify-between items-center gap-8">
          <div className="flex items-center gap-2">
            <Bell className="w-5 h-5 text-indigo-500" />
            <span className="font-bold text-lg">LetsNotify</span>
          </div>
          <div className="text-slate-500 text-sm">
            © 2026 LetsNotify SDK. All rights reserved. Built for high-performance Android apps.
          </div>
          <div className="flex gap-6">
            <Button variant="ghost" size="sm" className="text-slate-400 hover:text-white">API Reference</Button>
            <Button variant="ghost" size="sm" className="text-slate-400 hover:text-white">Support</Button>
          </div>
        </div>
      </footer>
    </div>
  );
}

function FeatureCard({ icon, title, description }: { icon: React.ReactNode, title: string, description: string }) {
  return (
    <motion.div 
      whileHover={{ y: -5 }}
      className="p-8 rounded-2xl bg-slate-900 border border-slate-800 hover:border-slate-700 transition-all shadow-xl"
    >
      <div className="bg-slate-800 w-12 h-12 rounded-xl flex items-center justify-center mb-6">
        {icon}
      </div>
      <h3 className="text-xl font-bold mb-3">{title}</h3>
      <p className="text-slate-400 leading-relaxed">
        {description}
      </p>
    </motion.div>
  );
}
